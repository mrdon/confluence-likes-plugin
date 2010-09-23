jQuery(document).ready(function() {
    jQuery('.like-comment').each(function(idx) {
        var alink = this.find('a:first');
        var pageId = enableLink.attr('href').substring(alink.attr('href').lastIndexOf('=') + 1);

        this.click(function() {
            jQuery.ajax({
                  url: link.attr('href'),
                  type: 'POST',
                  success: function(data) {
                    enableMirrorUI(true);
                  }
                });
            return false;
        });

        jQuery.ajax({
          url: contextPath + '/rest/likes/1/page/' + pageId + '.json',
          success: function(data) {
              var count = 0;
              jQuery.each(data.commentLikes, function(key) {
                  count++;
              });
              alink.html(" (" + count + ") likes");
          }
        });

    });
});