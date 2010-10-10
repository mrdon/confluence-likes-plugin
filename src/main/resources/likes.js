jQuery(document).ready(function() {
    jQuery('.like-comment').each(function(idx) {
        var alink = jQuery(this.firstElementChild);
        var pageId = alink.attr('href').substring(alink.attr('href').lastIndexOf('=') + 1);
        var commentId = alink.attr('id').replace(/like-comment-(.*)/, "$1");

        jQuery(this).click(function(event) {
            event.preventDefault();
            var toLike = alink.text() == "Like";
            alink.html('<img alt="waiting" src="' + contextPath + '/images/icons/wait.gif" />');
            jQuery.ajax({
                  url: alink.attr('href'),
                  type: toLike ? 'POST' : 'DELETE',
                  success: function(data) {
                      if (toLike) {
                        alink.html("Dislike");
                        addCount(commentId, 1);
                      } else {
                        alink.html("Like");
                        addCount(commentId, -1);
                      }
                    //enableMirrorUI(true);
                  }
                });
            return false;
        });

        function addCount(commentId, amount) {
            var date = jQuery('#comment-' + commentId + ' .date');
            var firstBit = date.text().substring(0, date.text().indexOf(" ago") + 4);
            var prev = parseInt(date.text().substring(firstBit.length).replace(/.*(\d+).*/, "$1"));
            prev = isNaN(prev) ? 0 : prev;
            var next = (prev + amount);
            date.html(firstBit + ' - ' + next + ' likes');
        }


        jQuery.ajax({
          url: contextPath + '/rest/likes/1/page/' + pageId + '.json',
          success: function(data) {
              var count = 0;
              jQuery.each(data.commentLikes, function(key) {
                  var link = jQuery('#like-comment-' + key);
                  link.html(this.liked ? "Dislike" : "Like");
                  addCount(key, this.users.length);
              });
              //alink.html(" (" + count + ") likes");

          }
        });

    });
});