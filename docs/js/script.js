$(document).ready(function() {
    let clases = ["Boss", "De", "Gang", "GangMember", "Ganster", "Joueur", "KartelIntelligence", "LesGangs", "LesJoueurs", "Lire","*Partie","Plateau","PotDeVin","Prison","+Recompense","-Reutilisable","-Showable" ]
    var lista = $('#classList'); 
    clases.forEach(ele => {
        let tipo = 'class';
        let nom = ele;
        if(ele.startsWith("*"))
            tipo = 'main class';
        else if(ele.startsWith("+"))
            tipo = 'abstract class';
        else if(ele.startsWith("-"))
            tipo = 'interface';
        
        if(tipo != 'class')
        nom = nom.substring(1);

        let reference =`../code/api/kartel/${nom}.html`;
        lista.append(`<a href = '${reference}' ><li class='list-group-item d-flex justify-content-between align-items-center'>${nom}
        <span class='badge badge-primary badge-pill'>${tipo}</span></li></a>`);
        
    });
});
