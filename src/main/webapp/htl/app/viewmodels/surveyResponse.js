define(function(require) {
    var router = require('plugins/router'),
        rest = require('rest'),
        utils = require('utils');

    var ctor = function () {
        this.displayName = 'Survey Response';
        var surveyResponseForm = ko.mapping.fromJS({
            surveyResponse: {
                survey: {
                    name: "",
                    deadline: ""
                },
                leaguePlayer: {
                    league: {
                        name: ""
                    },
                    player: {
                        name: ""
                    }
                }
            },
            entries: []
        });

        this.surveyResponseForm = surveyResponseForm;

        var initialized = false;

        function activate(leaguePlayerId,surveyId) {
            return rest.surveyResponses.get({
                leaguePlayerId: leaguePlayerId,
                surveyId: surveyId,
                selector: 'surveyResponse(leaguePlayer(league(id,name),player(id,name)),survey(name,deadline)),entries(question(name,answers(name,correct)),answerId,score)'
            }).done(function (data) {
                // Convert answerIds to strings so the knockout checked binding
                // will show initial values
                $.each(data.entries,function(index,entry){
                    entry.answerId = "{0}".format(entry.answerId);
                });

                ko.mapping.fromJS(data, {}, surveyResponseForm);

                ko.computed(function(){
                    if( !initialized ) {
                        // Subscribe to all observables in the survey response form
                        ko.mapping.toJS(surveyResponseForm);
                        initialized = true;
                    }
                    else {
                        updateSurveyResponse(ko.mapping.toJS(surveyResponseForm));
                    }

                }).extend({ throttle: 2000 });
            });
        }

        this.activate = activate;
    }

    function updateSurveyResponse(surveyResponseForm){
        return rest.surveyResponses.save({
            selector: 'surveyResponse(leaguePlayer(league(id,name),player(id,name)),survey(name,deadline)),entries(question(name,answers(name,correct)),answerId,score)',
            data: ko.mapping.toJS(surveyResponseForm)
        });
    }

    return ctor;
});