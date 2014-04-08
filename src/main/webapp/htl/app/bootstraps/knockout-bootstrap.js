/**
 * @author Jeff Ginn
 */

define(function(require, exports, module){
    var ko = require('knockout');
    window.ko = ko;

    // ko plugins
    ko.mapping    = require('knockout-mapping');

    return ko;
});