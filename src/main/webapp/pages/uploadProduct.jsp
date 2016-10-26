<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>uploadProduct</title>
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/angular.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-3.1.0.min.js"></script>
    <sec:csrfMetaTags />
</head>
<body>
<button onclick="uploadProd()">ololo</button>

<div ng-app="MyApp" ng-controller="MyCtrl">
    <!-- call $scope.search() when submit is clicked. -->
    <form ng-submit="search()">
        <!-- will automatically update $scope.user.first_name and .last_name -->
        <input type="text" ng-model="user.first_name">
        <input type="text" ng-model="user.last_name">
        <input type="submit" value="Search">
    </form>

    <div>
        Results:
        <ul>
            <!-- assuming our search returns an array of users matching the search -->
            <li ng-repeat="user in results">
                {{user.first_name}} {{user.last_name}}
            </li>
        </ul>
    </div>

</div>
    <script>
        angular.module('MyApp', [])
                .controller('MyCtrl', ['$scope', '$http', function ($scope, $http) {
                    $scope.user = {};
                    $scope.results = [];

                    $scope.search = function () {
                        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                        var csrfToken = $("meta[name='_csrf']").attr("content");
                        var headersCSRF = {};
                        headersCSRF[csrfHeader] = csrfToken;
                        var parameter = {
                            //"id": 5,
                            "@type": "Product",
                            "code": 555,
                            "text": "ololo",
                            "cpuSocket": "1123",
                            "maxRAM": 32
                        };
                        $http.post("/uploadProduct", JSON.stringify(parameter), {
                            headers: headersCSRF
                        }).
                        success(function(data) {
                            alert(data.code);
                        }).
                        error(function(data) {
                            alert("error");
                        });
                    }
                }]);
    </script>
</body>
</html>
