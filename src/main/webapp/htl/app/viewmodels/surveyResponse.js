define(function(require) {
    var router = require('plugins/router'),
        rest = require('rest'),
        utils = require('utils');

    var ctor = function () {
        this.displayName = 'Survey Response';
        var surveyResponse = ko.mapping.fromJS({
            id: "",
            survey: {
                name: "",
                questions: [],
                endTime: ""
            },
            leaguePlayer: {
                league: {
                    name: ""
                },
                player: {
                    name: ""
                }
            }
        });

        this.surveyResponse = surveyResponse;

        function activate(leaguePlayerId,surveyId) {
            return rest.surveyResponses.get({
                leaguePlayerId: leaguePlayerId,
                surveyId: surveyId,
                selector: 'confidencePoints,leaguePlayer(league,player),survey(name,endTime,questions(name,answers(name,correct)))'
            }).done(function (data) {
                ko.mapping.fromJS(data, {}, surveyResponse);
            });
        }

        this.activate = activate;
    }

    return ctor;
});