define(function(require, exports, module) {
    require('jquery');

    var defaultAjaxConfig = {
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        accept: "application/json; charset=utf-8"
    };

    var user = {
        _urls: {
            'login' : '/services/api/login'
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
            'getAll' : '/services/api/leagues'
        },

        'getAll' : function(settings){
            var ajaxConfig = $.extend( defaultAjaxConfig, {
                'type': 'GET',
                'url': league._urls.getAll
            });

            return $.ajax(ajaxConfig);
        }
    }
    return {
        user: user,
        league: league
    };
});
