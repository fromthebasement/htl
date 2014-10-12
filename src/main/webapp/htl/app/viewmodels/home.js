define(function() {
    "use strict";

    var router = require('plugins/router'),
        rest = require('rest'),
        appViewModel = require('appViewModel');

    var ctor = function () {
        this.displayName = 'Questions';

        var leaguePlayer = ko.computed(function(){
            var lp = appViewModel.user().leaguePlayer();

            if( lp.id )
                return lp;

            return null;
        });

        this.linkButtons = [{
            buttonClass: 'fa fa-list-ol',
            caption: "Questions",
            href: ko.computed(function() {
                return "#surveyResponses/leagues/{0}".format(leaguePlayer() ? leaguePlayer().league.id : null);
            })
        },{
            buttonClass: 'fa fa-sitemap',
            caption: "Standings",
            href: ko.computed(function() {
                return "#standings/leagues/{0}".format(leaguePlayer() ? leaguePlayer().league.id : null);
            })
        }];
        this.leaguePlayer = leaguePlayer;
    };

    return ctor;
});