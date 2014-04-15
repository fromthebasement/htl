define(function(require, exports, module) {
    var router = require('plugins/router'),
        app = require('durandal/app'),
        appViewModel = require('appViewModel');

    return {
        router: router,
        activate: function () {
            router.map([
                { route: '', title:'Standings', moduleId: 'viewmodels/standings', nav: true, adminOnly: false },
                { route: 'login(:/redirect)', title:'Login', moduleId: 'viewmodels/login', nav: false, adminOnly: false },
                { route: 'logout', title:'Logout', moduleId: 'viewmodels/logout', nav: false, adminOnly: false },
                { route: 'survey', title:'Questions', moduleId: 'viewmodels/survey', nav: true, adminOnly: false },
                { route: 'surveyManager', title:'Surveys', moduleId: 'viewmodels/surveyManager', nav: true, adminOnly: true }
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
        showNavBar: ko.observable(true),
        appViewModel: appViewModel
    };
});