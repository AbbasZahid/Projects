//$(document).ready(function() {
//    var data = {image : 'image', name : 'name', price : 'price', productId : 'productId'};
//    $.ajax({
//        dataType: "json",
//        url: 'http://localhost:8084/ShoppingCart/rest/service/product',
//        type: 'GET',
//        data: data,
//        success: function() {
//            alert('PUT completed');
//        }
//    });
//});

var app = angular.module("switchableGrid", ['ngResource']);

function SwitchableGridController($scope, $http){
	$scope.layout = 'grid';
        
        $scope.showResult = {};
        $scope.showResult1 = [{}];
        $scope.showResult2 = '';
        
        $scope.items = {
                image:      '',
                name:       '',
                price:      '',
                product_id: '',
                quantity:   '',
                amount:     ''
            };
            
        $scope.getshoppingcartItems = function (){
            $http({ url: '//localhost:8084/ShoppingCart/rest/service/shoppingcartItems',
                    method: "POST",
                    headers: {'Content-type': 'application/json'},
                    params: { product_id:$scope.product_id, quantity:$scope.quantity }
            }).success(function (data) {
                console.log("functions is succesful");
                $scope.showResult = data;
            }).error(function () {
                console.log("Service does not works");
            });
        };
        
        $scope.getShoppingcart = function (){
            $http({ url: '//localhost:8084/ShoppingCart/rest/service/shoppingcart',
                    method: "GET",
                    headers: {'Content-type': 'application/json'}
            }).success(function (data) {
                console.log(data);
                //TODO: Fix this body here
//                $scope.items.image = data[0].image;
//                $scope.items.name = data[0].name;
//                $scope.items.price = data[0].price;
//                $scope.items.product_id = data[0].product_id;
//                $scope.showResult1.push($scope.items);
            }).error(function () {
                console.log("Service does not works");
            });
        };
        
        $scope.productsGet = function (){
            $http({ url: '//localhost:8084/ShoppingCart/rest/service/product',
                    method: "GET",
                    headers: {'Content-type': 'application/json'}
            }).success(function (data) {
                console.log(data);
                $scope.items = data;
//                $scope.items.image = data[0].image;
//                $scope.items.name = data[0].name;
//                $scope.items.price = data[0].price;
//                $scope.items.product_id = data[0].product_id;
//                $scope.showResult1.push($scope.items);
            }).error(function () {
                console.log("Service does not works");
            });
        };
        
        $scope.shoppingcartItemsIdDelete = function (){
          $http({ url: '//localhost:8084/ShoppingCart/rest/service/shoppingcartItemsIdDelete',
                    method:"DELETE",
                    headers: {'Content-type': 'application/json'},
                    params: {product_id:$scope.product_id}
            }).success(function (data) {
                console.log(data);
                $scope.showResult2 = data;
            }).error(function (data){
               console.log(data);
               console.log("Product is not removed");
            });
        };
}