define(function(require, exports, module) {
    var router = require('plugins/router'),
        app = require('durandal/app'),
        appViewModel = require('appViewModel'),
        rest = require('rest');

    return {
        router: router,
        activate: function () {
            var deferred = $.Deferred();

            var baseRouterMap = [
                { route: '', title:'Home', moduleId: 'viewmodels/home', nav: true, adminOnly: false },
                { route: 'surveyResponses', title:'Questions', moduleId: 'viewmodels/surveyResponses', nav: true, adminOnly: false },
                { route: 'surveyResponses/leaguePlayers/:leaguePlayerId/surveys/:surveyId', title:'Survey Response', moduleId: 'viewmodels/surveyResponse', nav: false, adminOnly: false },
                { route: 'standings', title:'Standings', moduleId: 'viewmodels/standings', nav: true, adminOnly: false },
                { route: 'surveyFeeds', title:'Survey Feeds', moduleId: 'viewmodels/surveyFeeds', nav: true, adminOnly: false },
                { route: 'surveyFeeds/:id', title:'Survey Feed', moduleId: 'viewmodels/surveyFeed', nav: false, adminOnly: false },
                { route: 'surveys/:id', title:'Survey', moduleId: 'viewmodels/survey', nav: false, adminOnly: false },
                { route: 'login(:/redirect)', title:'Login', moduleId: 'viewmodels/login', nav: false, adminOnly: false },
                { route: 'logout', title:'Logout', moduleId: 'viewmodels/logout', nav: false, adminOnly: false }
            ];

//            getLeagues().done(function(data){
//                $.each(data, function(index, league){
//                    baseRouterMap.push( { route: 'league-' + league.id, title:league.name, moduleId: 'viewmodels/standings', nav: true, adminOnly: false, settings: { league: league } } )
//                });

                router.map(baseRouterMap).buildNavigationModel();

                $.ajaxSetup({
                    statusCode: {
                        401: function() {
                            router.navigate('#login');
                        }
                    }
                });

                router.activate().then(function(){
                    deferred.resolve();
                });
//            });

            return deferred.promise();
        },
        showNavBar: ko.observable(true),
        appViewModel: appViewModel
    };

    function getLeagues() {
        return rest.league.getAll();
    }
});