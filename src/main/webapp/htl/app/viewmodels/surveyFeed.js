define(function() {
    var router = require('plugins/router'),
        rest = require('rest'),
        surveySelector = 'name,active',
        surveyFeedSelector = 'surveys({0}),users(fullName),leagues(name)'.format(surveySelector)
;
    var ctor = function () {
        var _this = this;
        this.displayName = 'Survey Feed';

        var surveyFeed = ko.mapping.fromJS({
            id: "",
            name: "",
            users: [],
            leagues: [],
            surveys: []
        });

        this.surveyFeed = surveyFeed;

        this.surveyFeed.surveys.valueHasMutated();
        function activate(id){
            rest.surveyFeeds.get({
                id: id,
                selector: surveyFeedSelector
            }).done(function(data){
                ko.mapping.fromJS(data, {}, _this.surveyFeed);

                function updateSurveyFeed(){
                    var data = ko.mapping.toJS(surveyFeed);

                    return rest.surveyFeeds.update({
                        data: data
                    }).done(function(data){
                        ko.mapping.fromJS(data,{},_this.surveyFeed)
                    });
                }

                surveyFeed.name.subscribe(updateSurveyFeed);
            });
        }

        this.activate = activate;

        function addSurvey(surveyFeed){
            rest.surveys.create({
                selector: surveySelector,
                data: {
                    name: "New Survey",
                    surveyFeed: {
                        id: surveyFeed.id()
                    }
                }
            }).done(function(data){
                surveyFeed.surveys.push(ko.mapping.fromJS(data));
            });
        }

        this.addSurvey = addSurvey;

        function removeSurvey(survey,event) {
            var context = ko.contextFor(event.target),
                surveyFeed = context.$parent;

            rest.surveys.delete({
                data: ko.mapping.toJS(survey)
            }).done(function(data){
                surveyFeed.surveys.remove(survey);
            });
        }

        this.removeSurvey = removeSurvey;
    };

    return ctor;
});