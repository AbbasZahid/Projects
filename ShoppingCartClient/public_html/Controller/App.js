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
                $scope.showResult = data;
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