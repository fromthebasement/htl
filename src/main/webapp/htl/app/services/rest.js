define(function(require, exports, module) {
    require('jquery');

    var urlBuilder = require('services/urlBuilder');

    var defaultAjaxConfig = {
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        accept: "application/json; charset=utf-8"
    };

    var user = {
        _urls: {
            'login' : '/api/v1/login'
        },

        'login': function(settings){
            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'POST',
                'url': user._urls.login,
                data: JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        }
    };

    var league = {
        _urls: {
            'getAll' : '/services/api/leagues',
            'players' : '/services/api/leagues/{0}/players'
        },

        'getAll' : function(settings){
            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': league._urls.getAll
            });

            return $.ajax(ajaxConfig);
        },

        'getPlayers' : function(settings){
            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': league._urls.players.format(settings.league.id)
            });

            return $.ajax(ajaxConfig);
        }
    }

    var surveyFeeds = {
        _urls: {
            'get'       : '/api/v1/surveyFeeds/{0}',
            'getAll'    : '/api/v1/surveyFeeds'
        },

        'getAll' : function(settings){
            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': surveyFeeds._urls.getAll
            });

            return $.ajax(ajaxConfig);
        },

        'get' : function(settings){
            var url = urlBuilder.appendSelector( surveyFeeds._urls.get.format(settings.id), settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': url
            });

            return $.ajax(ajaxConfig);
        }
    }

    var surveys = {
        _urls: {
            'get'    : '/api/v1/surveys/{0}',
            'create' : '/api/v1/surveys',
            'update' : '/api/v1/surveys',
            'delete' : '/api/v1/surveys'
        },

        'get' : function(settings){
            var url = urlBuilder.appendSelector( surveys._urls.get.format(settings.id), settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': url
            });

            return $.ajax(ajaxConfig);
        },

        'create' : function(settings) {
            var url = urlBuilder.appendSelector( surveys._urls.create, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'POST',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'update' : function(settings) {
            var url = urlBuilder.appendSelector( surveys._urls.update, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'PUT',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'delete' : function(settings) {
            var url = surveys._urls.delete;

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'DELETE',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        }
    }

    var questions = {
        _urls: {
            'create' : '/api/v1/questions',
            'update' : '/api/v1/questions',
            'delete' : '/api/v1/questions'

        },

        'create' : function(settings) {
            var url = urlBuilder.appendSelector( questions._urls.create, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'POST',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'update' : function(settings) {
            var url = urlBuilder.appendSelector( questions._urls.update, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'PUT',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'delete' : function(settings) {
            var url = questions._urls.delete;

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'DELETE',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        }
    }

    var answers = {
        _urls: {
            'create' : '/api/v1/answers',
            'delete' : '/api/v1/answers',
            'update' : '/api/v1/answers'
        },

        'create' : function(settings) {
            var url = urlBuilder.appendSelector( answers._urls.create, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'POST',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'update' : function(settings) {
            var url = urlBuilder.appendSelector( answers._urls.update, settings.selector );

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'PUT',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        },

        'delete' : function(settings) {
            var url = answers._urls.delete;

            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'DELETE',
                'url': url,
                'data': JSON.stringify(settings.data || "")
            });

            return $.ajax(ajaxConfig);
        }
    }

    var surveyResponses = {
        _urls: {
            'getActive'    : '/api/v1/surveyResponses/active'
        },

        'getActive' : function(settings){
            var url = urlBuilder.appendSelector( surveyResponses._urls.getActive, settings.selector );


            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': url
            });

            return $.ajax(ajaxConfig);
        }
    }

    return {
        user: user,
        league: league,
        surveyFeeds: surveyFeeds,
        surveys: surveys,
        questions: questions,
        answers: answers,
        surveyResponses: surveyResponses
    };
});
