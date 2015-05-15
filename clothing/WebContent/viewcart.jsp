<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <link href="fonts/glyphicons-halflings-regular.ttf"/>
        <link  href="fonts/glyphicons-halflings-regular.woff"/>
        <script src="scripts/jquery-2.1.3.min.js"></script> 

    </head>
    <body> 
  
        <div class="col-md-12 col-lg-12">
            <h1 style="color: darkgray">My cart</h1>
            <div class="col-md-12">
                <form action="/Clothing/payment.jsp"  target="body">
                    <table id="cart" class="table" >
                        <tr>
                        <input type="hidden" id='noOfitems' name='noOfitems'>
                        </tr>
                        <tr>
                            <th></th>
                            <th class="col-md-2 col-lg-2">Item</th>
                            <th class="col-md-1 col-lg-1">Price</th>
                            <th class="col-md-1 col-lg-1">Color</th>
                            <th class="col-md-1 col-lg-1">Size</th>
                            <th class="col-md-2 col-lg-2">Style</th>
                            <th class="col-md-1 col-lg-1">Quantity</th>
                            <th class="col-md-1 col-lg-1">Total</th>
                            <th>Action</th>
                        </tr>
                        <!--<tr id="row1"> <td><input type="hidden" id="id"></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td><input type="number" min="1" value="1" class="form-control" name="qty_" id="qty_"></td> <td><button class=" btn btn-success" onclick='deleteTableRow("row"+1)'><span class="glyphicon glyphicon-trash" ></span></button></td> </tr>-->
<!--                        <tr id="btnarea"><td ><input type="submit" class="btn btn-success pull-right"></td></tr>-->
<!--<tr> <td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td>Grand Total</td> <td><input type='number' class="disabled" disabled="true" value=""></td></tr>-->
                    </table>
                </form>
            </div>
        </div>

        <script>
            var noOfitems = 0;
            $(document).ready(function() {
                var cartString = localStorage.getItem("cart");
                var cart;
//                console.log(!cartString);
//                if (!cartString) {
//                    cart = {};
//                } else {
                cart = JSON.parse(cartString);
//                }
                var i = 0;
                var grandTotal=0;
                $.each(cart, function(key, value) {
                    if(null!=value){
//                    console.log(JSON.stringify(value));
//                    console.log("   "+value.selectedItem.color.name);
                    var rowname = "row_" + i;
                    if('undefined'==value.quantity){
                    	value.quantity =1;
                    }
                    var total=value.price*value.quantity;
                  
                    grandTotal+=total;
                   var index=key;
                   var styleName='';
                   if(null!=value.style){
                	   styleName =value.style.name;
                   }
                     $("#cart").append('<tr id="' + rowname + '"> <td><input type="hidden" id="id_' + i + '" name="id_' + i + '" value="' + key + '"></td> <td>' + value.name + '</td> <td>$' + value.price + '</td> <td>' + value.selectedItem.color.name + '</td> <td>' + value.selectedItem.size.sizeCode + '</td> <td>' + styleName + '</td><td><input  type="number" value='+value.quantity+' min="1" class="form-control" name="qty_' + i + '" id="qty_'+i+'" onchange=\"updateQuantity('+i+','+value.price+')\"><td id="total_'+i+'">$' + total + '</td>  <td><button class=" btn btn-toolbar" onclick=\'deleteTableRow("' + rowname + '","' + i + '","' + value.price + '","' + key + '")\'><span class="glyphicon glyphicon-trash" ></span></button></td> </tr>');
                    
                     i++;
                     
                }
                });
                noOfitems=i--;
//                $('#cart').append('<tr> <td colspan="7" class="pull-right" ></td> <td><input type="number" class="disabled" value="'+grandTotal+'"></td></tr>')
                $('#cart').append('<tr> <td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td><label class="control-lable pull-right"> Grand Total</label></td> <td><label class="control-lable pull-left">$<span id="grandTotal">'+grandTotal+'</span></label></td><td></td></tr>');
				if(0!=noOfitems )
                 $('#cart').append('<tr id="btnarea"><td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td></td><td><a href="https://paypal.com" target="_blank"><img src="images/paypal.jpeg"/></a></td><td ><input type="submit" value="Pay" class="btn btn-toolbar btn-lg pull-left pull-right" ></td></tr>');
               
                $("#noOfitems").val(noOfitems);
                console.log($("#noOfitems").val());
            });
            function deleteTableRow(id,i,price,itemKey) {
            	
            	var quantity= $("#qty_"+i).val();         
               
                var grandTotal =$("#grandTotal").html();
                var x=parseInt(grandTotal)-(price*parseInt(quantity));
               
                $("#grandTotal").html(x);
             
                
                var cartStr = localStorage.getItem("cart");
                var existingCart;
                console.log(!cartStr);
                if (!cartStr) {
                	existingCart = {};
                } else {
                	existingCart = JSON.parse(cartStr);
                	console.log(existingCart);
                	existingCart[itemKey]=null;
                	console.log(existingCart);
                	 var itemCount=0;
              	   $.each(existingCart, function(key, value) {
                         if(null!=value){
                      	   if('undefined'!=value.quantity){
                      		   itemCount++;
                             }
                         }
                     });
              	   $("#noOfitems").val(itemCount);
                }
                localStorage.setItem("cart", JSON.stringify(existingCart));
               
              	var itemCount=$("#noOfitems").val();
              
               x= parseInt(itemCount)-parseInt(quantity);
               $("#noOfitems").val(x);
               $("#" + id).remove();
               console.log($("#noOfitems").val());
            }
            function updateQuantity(id,price) {
            	var grandTotal =$("#grandTotal").html();                
               	var total= $("#total_"+id).html();
               	var key=$("#id_"+id).val();  
               
               	var quantity=$("#qty_"+id).val();               	
               	total=total.substring(1,total.length);   
               	grandTotal= parseInt(grandTotal)-parseInt(total);           
               	var x= parseInt(price)*parseInt(quantity);
             	grandTotal= parseInt(grandTotal)+parseInt(x); 
               	$("#total_"+id).html('$'+x);
              
                $("#grandTotal").html(grandTotal);

                var cartStr = localStorage.getItem("cart");
                var existingCart;
                console.log(!cartStr);
                if (!cartStr) {
                	existingCart = {};
                } else {
                	existingCart = JSON.parse(cartStr);                
                	var cartEntry=existingCart[key];
                	cartEntry['quantity']=quantity;
                	 existingCart[key]=cartEntry;
                	 var itemCount=0;
                	   $.each(existingCart, function(key, value) {
                           if(null!=value){
                        	   if('undefined'!=value.quantity){
                        		   itemCount++;
                               }
                           }
                       });
                	   $("#noOfitems").val(itemCount);
                	 localStorage.setItem("cart", JSON.stringify(existingCart));
                }
               
               
            }
        </script>
       
    </body>
</html>
