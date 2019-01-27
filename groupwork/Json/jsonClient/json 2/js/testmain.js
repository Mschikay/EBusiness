var ourRequest =  new XMLHttpRequest();

btn.addEventListener("click", function() {
    ourRequest.open('GET', 'https://learnwebcode.github.io/json-example/animals-1.json');
    ourRequest.onload=function(){
        if(ourRequest.status >=200 && ourRequest.status < 400){
            var ourData = JSON.parse(ourRequest.responseText);
            renderHTML(ourData);
        }else{
            console.log("We connected to the server, but it returned an error.");
        }

    };

    ourRequest.onerror =function() {
        console.log("Connection error");
    }

    ourRequest.send();


});

function renderHTML(data){
    var htmlString="";
    var an = document.getElementById("sid").value;
    var lod = document.getElementById("sid2").value
    for(i=0; i<data.length;i++) {

        if (data[i].name != an) continue;
        else if (lod == "like") {
            htmlString += "<p>" + data[i].name + " is a " + data[i].species + " that likes to eat ";
            for (ii = 0; ii < data[i].foods.likes.length; ii++) {
                if (ii == 0) {
                    htmlString += data[i].foods.likes[ii];
                } else {
                    htmlString += " and " + data[i].foods.likes[ii];
                }
            }

        } else {
            htmlString += "<p>" + data[i].name + " is a " + data[i].species + " that does not like to eat ";
            for (ii = 0; ii < data[i].foods.dislikes.length; ii++) {
                if (ii == 0) {
                    htmlString += data[i].foods.dislikes[ii];
                } else {
                    htmlString += " and " + data[i].foods.dislikes[ii];
                }
            }
        }
        htmlString += '.</p>';
        document.getElementById("demo").innerHTML = htmlString;


    }


    var htmlString =  "<p>" + data[i].name + " is a " + data[i].species + " that likes to eat " +
		data[i].foods.likes[ii] + " and " + data[i].foods.dislikes[ii];






    }
