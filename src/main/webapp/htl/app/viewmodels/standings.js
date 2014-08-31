define(function() {
    var router = require('plugins/router'),
        rest = require('rest'),
        appViewModel = require('appViewModel');

    var ctor = function () {
        this.displayName = 'Competing!';
        var standings = ko.observableArray();
        this.standings = standings;

        function activate(){
            var league = appViewModel.user().leaguePlayer.league;
            if( !ko.unwrap(league.id) )
                router.navigate('#login');
            else {
                rest.league.getStandings({
                    league: ko.mapping.toJS(league),
                    selector: 'entries(score,leaguePlayer(id,player(id,name)))'
                }).done(function (data) {
                    standings(data.entries);
                });
            }
        }

        this.activate = activate;
    };

    return ctor;
});