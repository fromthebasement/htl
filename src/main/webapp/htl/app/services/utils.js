/**
 * @author Jeff Ginn
 */

define(function(require, exports, module) {
    function selectElementContents(el) {
        var range = document.createRange();
        range.selectNodeContents(el);
        var sel = window.getSelection();
        sel.removeAllRanges();
        sel.addRange(range);
    }

    return {
        selectElementContents: selectElementContents
    };
});