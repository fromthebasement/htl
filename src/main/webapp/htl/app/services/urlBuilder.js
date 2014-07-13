define(function(require, exports, module) {
    /**
     * Appends selectors url parameter to the given url
     *
     * @param url           The url
     * @param selectors     The selectors
     * @returns {string}    The url with selectors
     */
    function appendSelector(url,selector) {
        if( !selector )
            return url;

        return url + '?selector=' + selector;
    }

    return {
        appendSelector: appendSelector
    };
});
