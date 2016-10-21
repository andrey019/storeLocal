
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testModel1</title>
    <%--<script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>--%>
    <script type="text/javascript" src="/resources/js/angular.min.js"></script>
    <script type="text/javascript" src="/resources/js/dirPagination.js"></script>
</head>
<body>

<div ng-app="myApp" ng-controller="customersCtrl">
    <label>Search all: <input ng-model="search.$"></label>
    <%--<label>Search number: <input ng-model="search.number"></label>--%>
    <%--<label>Search text: <input ng-model="search.text"></label>--%>
    <table>
        <%--<tr dir-paginate="entry in models | numberFilter:numbers | orderBy:number:true | itemsPerPage:2">--%>
        <tr ng-repeat="entry in models | numberFilter:numbers | orderBy:'number':reverse">
            <td>{{ entry.number }}</td>
            <td>{{ entry.text }}</td>
        </tr>
    </table>

    <br/>
    <h4>Filters</h4>
    <input type="checkbox" data-ng-model="numbers.six" /> 6
    <input type="checkbox" data-ng-model="numbers.five" /> 5
    <%--<input type="checkbox" data-ng-model="search.text" data-ng-true-value="2" data-ng-false-value="" /> gno--%>
    <%--<pre>{{numbers|json}}</pre>--%>
    <dir-pagination-controls
            max-size="5"
            direction-links="true"
            boundary-links="true" >
    </dir-pagination-controls>
</div>

<script>
    var app = angular.module('myApp', ['angularUtils.directives.dirPagination']);
    app.controller('customersCtrl', function($scope, $http) {
        $scope.numbers = {five: false, six:false};
        $http.get("/getTestModel1")
                .then(function (response) {
                    //alert(JSON.stringify(response.data));
                    $scope.models = response.data;
                    angular.forEach($scope.models, function(lol) {
                        lol.number = parseFloat(lol.number);
                    });
                });
    });


    app.filter('numberFilter', function() {
        return function(items, numbers) {
            var filtered = [];

            angular.forEach(items, function(item) {
                if(numbers.five == false && numbers.six == false) {
                    filtered.push(item);
                }
                else if(numbers.five == true && numbers.six == false && item.number > 5416954889){
                    filtered.push(item);
                }
                else if(numbers.six == true && numbers.five == false && item.number == '6'){
                    filtered.push(item);
                }
            });

            return filtered;
        };
    });

</script>

</body>
</html>
