define(function() {
    var router = require('plugins/router'),
        rest = require('rest'),
        surveySelector = 'name,active',
        surveyFeedSelector = 'archived,surveys({0}),users(fullName),leagues(name)'.format(surveySelector)
;
    var ctor = function () {
        var _this = this;
        this.displayName = 'Survey Feed';

        var surveyFeed = ko.mapping.fromJS({
            id: "",
            name: "",
            users: [],
            leagues: [],
            surveys: [],
            archived: false
        });

        this.surveyFeed = surveyFeed;

        function updateSurveyFeed(){
            var data = ko.mapping.toJS(surveyFeed);

            return rest.surveyFeeds.update({
                data: data
            }).done(function(data){
                ko.mapping.fromJS(data,{},surveyFeed)
            });
        }

        this.surveyFeed.surveys.valueHasMutated();
        function activate(id){
            rest.surveyFeeds.get({
                id: id,
                selector: surveyFeedSelector
            }).done(function(data){
                ko.mapping.fromJS(data, {}, _this.surveyFeed);
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

        function archiveSurveyFeed(archive){
            var data = ko.mapping.toJS(surveyFeed);

            data.archived = archive;

            return rest.surveyFeeds.archive({
                data: data,
                selector: 'archived'
            }).done(function(data){
                ko.mapping.fromJS(data,{},surveyFeed)
            });
        }

        function archive(surveyFeed) {
            if( surveyFeed.archived() )
                return;

            return archiveSurveyFeed(true);
        }

        function makeActive(surveyFeed) {
            if( !surveyFeed.archived() )
                return;

            return archiveSurveyFeed(false);
        }

        this.archive = archive;
        this.makeActive = makeActive;
    };

    return ctor;
});