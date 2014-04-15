define(function(require, exports, module) {
    var router = require('plugins/router'),
        appViewModel = require('appViewModel'),
        rest = require('rest');

    function activate(redirect){
        appViewModel.reset();
    }

    return {
        activate: activate
    }
});