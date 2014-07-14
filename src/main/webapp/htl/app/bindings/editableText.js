define(['knockout'], function(ko){
    // undo when pressing the escape key for content editable
    $(document).on('keydown', '[contenteditable=true]', function(e) {
        var esc = e.keyCode == 27;

        if( esc ) {
            // restore state
            document.execCommand('undo');
            e.target.blur();
        }
    });

    // When pressing enter on a contenteditable oneliner, blur
    $(document).on('keydown', '[contenteditable-oneliner]', function(e) {
        var nl = e.keyCode == 13;

        if( nl ) {
            e.target.blur();
        }
    });

    // placeholder support for content editable
    // https://github.com/sprucemedia/jQuery.divPlaceholder.js
    $(document).on('change keydown keypress input', '*[data-placeholder]', function() {
        if (getTextContent(this)) {
            this.setAttribute('data-div-placeholder-content', 'true');
        }
        else {
            this.removeAttribute('data-div-placeholder-content');
        }
    });

    // This method gets text content even when textContent is not defined (e.g. ie8).
    // The more general solution of adding the textContent property to all
    // objects if it doesn't exist does not work well with jQuery.
    // http://stackoverflow.com/questions/18063402/how-to-get-textcontent-to-work-in-internet-explorer-8-javascript
    function getTextContent(el) {
        // Check if textContent is not properly defined (ie8)
        if(  Object.getOwnPropertyDescriptor(Element.prototype, "textContent") &&
            !Object.getOwnPropertyDescriptor(Element.prototype, "textContent").get) {
            return el.innerText;
        }
        else
            return el.textContent;
    }

    /**
     * This method cleanses html characters when pasting into content editable fields
     */
    $(document).on('paste', '[contenteditable-plain]', function(e) {
        var text,
            handledPaste = false;

        // Grab clipboardData from the event if supplied (this works well in Chrome)
        var clp = (e.originalEvent || e).clipboardData;

        // If the event did not have clipboard data, try to get the data from the window
        if (clp === undefined || clp === null) {
            text = window.clipboardData.getData("text") || "";

            // If the window has clipboard data paste it to a range to strip html
            if (text !== "") {
                handledPaste = true;
                document.selection.createRange().pasteHTML(text.replace(/(\r\n|\r|\n)/g,"<br/>"));
            }
        }
        // The event had clipboard data.  We're dealing with a modern browser and
        // can handle the paste using insertHTML
        else {
            text = clp.getData('text/plain') || "";
            if (text !== "") {
                handledPaste = true;
                document.execCommand('insertHTML', false, text);
            }
        }

        // If we were able to get access to the clipboard to handle the paste operation
        // then prevent the browser from handling paste
        if( handledPaste ) {
            e.preventDefault();
        }
        // If we couldn't get access to the clipboard for any reason (e.g. programmatic
        // clipboard access disabled in IE) then we'll let the browser handle the paste
        // event.
        else {
        }
    });

    function filterHtml(html) {
        var filteredHtml = html;

        // Replace <br>, </p>, and </P> with \n (Firefox uses <br>, IE9 uses <p></p>, and IE8 uses <P></P>)
        filteredHtml = filteredHtml.replace(/<br>/g,"\n" ).replace(/<\/p>/g,"\n" ).replace(/<\/P>/g,"\n" );

        // Remove <p>, <P>, and &nbsp; (IE9 adds &nbsp;)
        filteredHtml = filteredHtml.replace(/<p>/g, "").replace(/<P>/g, "").replace(/&nbsp;/g, " " );

        // trim (IE8 does not have a trim() function)
        filteredHtml = filteredHtml.replace(/^\s+|\s+$/g, '');

        return filteredHtml;
    }

    ko.bindingHandlers.editableText = {
        init: function(element, valueAccessor) {
            $(element).on('blur', function() {
                var observable = valueAccessor();

                var filteredHtml = filterHtml($(this).html());

                observable( $(this).html(filteredHtml).text() );
            });
        },
        update: function(element, valueAccessor) {
            var value = ko.utils.unwrapObservable(valueAccessor());
            value = value || "";
            $(element).text(value).trigger("change");
        }
    };
});