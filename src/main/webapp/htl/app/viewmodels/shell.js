define(function(require, exports, module) {
    var router = require('plugins/router'),
        app = require('durandal/app');

    return {
        router: router,
        activate: function () {
            router.map([
                { route: '', title:'Welcome', moduleId: 'viewmodels/welcome', nav: true },
                { route: 'login(:/redirect)', title:'Login', moduleId: 'viewmodels/login', nav: false },
                { route: 'flickr', moduleId: 'viewmodels/flickr', nav: true }
            ]).buildNavigationModel();

            return router.activate().then( function(){
                $.ajaxSetup({
                    statusCode: {
                        401: function() {
                            router.navigate('#login');
                        }
                    }
                });
            });
        },
        showNavBar: ko.observable(true)
    };
});