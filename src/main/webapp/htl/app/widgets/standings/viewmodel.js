define(function(require, exports, module) {
    var ctor = function() { };

    ctor.prototype.activate = function(settings) {
        this.settings = settings;
    };

    return ctor;
});