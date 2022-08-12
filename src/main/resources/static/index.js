const baseURL = "http://localhost:8080";
const entry_title = document.getElementById("entry-title");
const entry_body = document.getElementById("entry");
const update = document.getElementById("update");
const back = document.getElementById("back");
const submit = document.getElementById("submit");
let entry_list;
var elements = document.querySelectorAll('[data-tw-bind]'), scope = {};
let date = new Date();
let today = date.getDate()+"/"+date.getMonth()+"/"+date.getFullYear();

elements.forEach(function(element) {
    //execute scope setter
    if(element.type === 'text'|| element.type === 'textarea'){
        var propToBind = element.getAttribute('data-tw-bind');
        addScopeProp(propToBind);
        element.onkeyup = function(){
            scope[propToBind] = element.value;
        }
    };

    //bind prop to elements
    function addScopeProp(prop){
        //add property if needed
        if(!scope.hasOwnProperty(prop)){
            //value to populate with newvalue
            var value;
            Object.defineProperty(scope, prop, {
                set: function (newValue) {
                    value = newValue;
                    elements.forEach(function(element){
                        //change value to binded elements
                        if(element.getAttribute('data-tw-bind') === prop){
                            if(element.type && (element.type === 'text' ||
                                element.type === 'textarea')){
                                element.value = newValue;
                            }
                            else if(!element.type){
                                element.innerHTML = newValue;
                            }
                        }
                    });
                },
                get: function(){
                    return value;
                },
                enumerable: true
            });
        }
    }
});

const setup = ()=>{
    console.log("Doc loaded")
    update.style.display = "none";
    back.style.display = "none";
    submit.style.display = "block"; 

    // fetch(baseURL+"/entries")
    // .then(data=>{

    //     entry_list = data.body.entries;
    //     let container = document.getElementById('entryList');

    //     if (entries.length){
    //         li = document.createElement('li');
    //         li.textContent = "No entries yet!!!";
    //         container.appendChild(li);
    //     }

    //     for(let page in entry_list){
    //         //Creating an li
    //         li = document.createElement('li');
    //         li.textContent = page.title + " " + page.date;
    //         li.onclick = loadPrevious(page)
    //         container.appendChild(li);

    //         if (page.date === today){
    //             scope['title'] = page.title;
    //             scope['data'] = page.content;
    //         }
    //     }

    // });
}

// TODO: Complete this method
const updateEntry = () =>{
    console.log("update clicked");

}

// TODO: verify the correctness
const submitEntry = () =>{
    console.log("Submit clicked")
    console.log(scope['title']);
    console.log(scope['data'])
    let date = new Date();
    if (scope['title'].trim() === ""){
        alert("PLEASE ENTER A TITLE!!")
        return;
    }
    if(scope['data'].trim === ""){
        alert("No content to add in diary!!!");
        return;
    }

    // fetch(baseURL+"/add",{
    //     method:"POST",
    //     body:{
    //         entry:{
    //             id: entry_list[entry_list.length - 1].id + 1,
    //             heading: scope['title'],
    //             content: scope['data'],
    //         }
    //     }
    // }).then((response) =>{
    //     if (response.status == 200){
    //         alert("Entry added");

    //     }else{
    //         alert("There was some issue on the server end!!");
    //     }
    // })

}

//TODO: verify the correctness of this function
const loadPrevious = (entry) =>{
    back.style.display = "block";
    submit.style.display = "none"; 

    if (entry.date === today){
        update.style.display = "block";
    }else{
        entry_title.disabled = true;
        entry_body.disabled = true;
    }
    scope['title'] = entry.heading;
    scope['data'] = entry.content;
}
//TODO: complete this function
const loadCurrent = () =>{
    back.style.display = "none";
    submit.style.display = "block"; 
    entry_title.disabled = false;
    entry_body.disabled = false;



}

const unhighlight = () =>{
    document.getElementById("entry-title").style = "font-family: 'Space Mono', monospace;font-size: 1rem; color: black;border: none;"
}

const highlight = () =>{
    document.getElementById("entry-title").style = "font-family: 'Space Mono', monospace;font-size: 1rem; color: lightcoral;border: 0.15vw solid lightcoral;";
}