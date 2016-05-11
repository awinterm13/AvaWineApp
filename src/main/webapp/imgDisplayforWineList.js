/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//

// A method to display images with rows of the table on wineListPage.jsp when a row is clicked.
  $(document).ready(function () {
                      $('.table > tbody > tr').click(function () {
                    image = $(this).attr('class');
                    $('#picture').attr("src",image.toString());
                    
                });
            });
