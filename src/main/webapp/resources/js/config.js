/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function(){
    $('.datepicker').datepicker({
     format: "mm/yyyy",
     startDate: "01/2005",
     endDate: "12/2014",
     startView: 1,
     minViewMode: 1,
     clearBtn: true
    });
});

$(function(){
    $('.selectpicker').selectpicker();    
});