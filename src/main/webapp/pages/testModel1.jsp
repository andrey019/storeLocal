
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testModel1</title>
    <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
</head>
<body>

<div ng-app="myApp" ng-controller="customersCtrl">

    <table>
        <tr ng-repeat="x in names">
            <td>{{ x.number }}</td>
            <td>{{ x.text }}</td>
        </tr>
    </table>

</div>

<script>
    var app = angular.module('myApp', []);
    app.controller('customersCtrl', function($scope, $http) {
        $http.get("/getTestModel1")
                .then(function (response) {$scope.names = response.data;});
    });
</script>

</body>
</html>
