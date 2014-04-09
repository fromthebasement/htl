define(function(require, exports, module) {
    require('jquery');

    var user = {
        _urls: {
            'login' : '/services/api/login.json'
        },

        'login': function(settings){
            var ajaxConfig = {
                'type': 'POST',
                'url': user._urls.login,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(settings.data || "")
            };

            return $.ajax(ajaxConfig);
        }
    };


    return {
        user: user
    };
});
