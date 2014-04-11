define(function(require, exports, module) {
    var router = require('plugins/router'),
        app = require('durandal/app');

    return {
        router: router,
        activate: function () {
            router.map([
                { route: '', title:'Standings', moduleId: 'viewmodels/standings', nav: true },
                { route: 'login(:/redirect)', title:'Login', moduleId: 'viewmodels/login', nav: false },
                { route: 'survey', moduleId: 'viewmodels/survey', nav: true }
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