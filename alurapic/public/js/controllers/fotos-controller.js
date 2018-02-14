angular.module('alurapic').controller('FotosController', function($scope, $http) {

    $scope.fotos = [];
    $scope.filtro = '';
    $scope.mensagem = '';

    $http.get('v1/fotos')
    .success(function(fotos) {
        $scope.fotos = fotos;
    })
    .error(function(erro) {
        console.log(erro);
    })

    $scope.remover = function(foto) {
        $http.delete('v1/fotos/' + foto._id)
            .success(function() {
                var indiceFoto = $scope.fotos.indexOf(foto);
                $scope.fotos.splice(indiceFoto, 1);
                $scope.mensagem = 'Foto ' + foto.titulo + ' removida com sucesso!';
                console.log('Foto ' + foto.titulo + ' removida com sucesso!');
            })
            .error(function(erro) {
                console.log('Erro ao remover a foto ' + foto.titulo);
                $scope.mensagem = 'Não foi possível remover a foto ' + foto.titulo;
                console.log(erro);
            })
    };

    /*
    var promise = $http.get('v1/fotos');

    promise.then(function(retorno) {
        $scope.fotos = retorno.data;
    }).catch(function(error) {
        console.log(error);
    });
    */
});