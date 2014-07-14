/**
 * @author Jeff Ginn
 */

define(function(require, exports, module){
    require('knockout-sortable');

    ko.bindingHandlers.sortable.options = {
        distance: 3,
        placeholder: "view-placeholder",
        scrollSensitivity: 20,
        refreshPositions: true,
        tolerance: "pointer",
        cancel: '[contenteditable=true]',
        items: '> div',
        start: function(event,ui){
        },
        sort: $.noop,
        stop: $.noop,
        update: function(event,ui){
        }
    }

    require('bindings/editableText');
});
