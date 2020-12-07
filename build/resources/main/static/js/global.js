(function ($) {
    'use strict';

    /*[ File Input Config ]
        ===========================================================*/
    
    try {
    
        var file_input_container = $('.js-input-file');
    
        if (file_input_container[0]) {
    
            file_input_container.each(function () {
    
                var that = $(this);
    
                var fileInput = that.find(".input-file");
                var info = that.find(".input-file__info");
    
                fileInput.on("change", function (event) {
                     
                    var files = event.currentTarget.files;
    				var fileType = files[0].type;
                    var fileName;
    				                    
    				
    				
                    if (fileType.length==0)	{
                    	fileName = '../' + files[0].name;
                    } else {			
						fileName = '../' + files[0].webkitRelativePath.substring(0, files[0].webkitRelativePath.lastIndexOf("/"));
                    }

    
                    if(fileName == "") {
                        info.text("No file chosen");
                    } else {
                        info.text(fileName);
                    }
    
                })
    
            });
    
        }
    
    
    
    }
    catch (e) {
        console.log(e);
    }

})(jQuery);