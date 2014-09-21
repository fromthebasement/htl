/**
 * @author Jeff Ginn
 */

define(function(require, exports, module){
    require('knockout-bootstrap');
    require('knockout-sortable');

    ko.bindingHandlers.sortable.connectClass = null;

    ko.bindingHandlers.sortable.options = {
        distance: 3,
        placeholder: "view-placeholder",
        scrollSensitivity: 20,
        refreshPositions: true,
        tolerance: "pointer",
        cancel: '[contenteditable=true]',
        start: function(event,ui){
        },
        sort: $.noop,
        stop: $.noop,
        update: function(event,ui){
        }
    }

    require('bindings/editableText');
    require('bindings/surveyRow');
    require('bindings/question');
    require('bindings/answer');
    require('bindings/datetimepicker');
    require('bindings/dateTime');
});
