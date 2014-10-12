define(function() {
    var router = require('plugins/router'),
        rest = require('rest'),
        appViewModel = require('appViewModel');

    var ctor = function () {
        this.displayName = 'Competing!';
        var standings = ko.observableArray();
        this.standings = standings;

        var surveyStandings = ko.observableArray();
        this.surveyStandings = surveyStandings;

        function activate(leagueID){
            var league;
            if( !leagueID )
                league = appViewModel.user().leaguePlayer().league;
            else
                league = {
                    id: leagueID
                };

            return rest.league.getStandings({
                league: ko.mapping.toJS(league),
                selector: 'entries(score,leaguePlayer(id,player(id,name))),surveyStandings(survey(name,deadline),entries(score,leaguePlayer(id,player(id,name))))'
            }).done(function (data) {
                standings(data.entries);
                surveyStandings(data.surveyStandings);
            });
        }

        this.activate = activate;
    };

    return ctor;
});