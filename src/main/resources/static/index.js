//declaring variables
const baseURL = "http://localhost:8080";
// const baseURL = "https://e30d-115-98-233-166.in.ngrok.io";
const entry_title = document.getElementById("entry-title");
const entry_body = document.getElementById("entry");
const update = document.getElementById("update");
const back = document.getElementById("back");
const submit = document.getElementById("submit");
// let scope['entry_list'];
let current_id = 0;
var elements = document.querySelectorAll('[data-tw-bind]'), scope = {};
let date = new Date();
let today = date.getDate()+"/"+date.getMonth()+"/"+date.getFullYear();

//implementing methods
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

function AddButtonHandler(elem, Dentry) {
    elem.addEventListener('mouseenter', function(e) {
        const btn = document.createElement("button");
        btn.innerText = "x";
        btn.id = "delete";
        btn.onclick = () => {
            fetch(baseURL+"/diaryentry/"+Dentry.id,{
                method: "DELETE"
            }).then(status => {
                if (status === 1){
                    alert(`Entry ${Dentry.title} deleted successfully!!`);
                    const entries = document.getElementById("entries");
                    entries.removeChild(e.target);
                    fetchList();
                }else{
                    alert("There was some issue on the backend!!");
                }
            })
        }
        e.target.appendChild(btn);
    }, false);
}

function LoadPreviousHandler(elem, entry){
    elem.addEventListener("click", e =>{
        // console.log(entry)
        back.style.display = "block";
        submit.style.display = "none"; 
        let entry_date = new Date(entry.date);
        entry_date = entry_date.getDate()+"/"+entry_date.getMonth()+"/"+entry_date.getFullYear();

        if (entry_date === today){
            update.style.display = "block";
            entry_title.disabled = false;
            entry_body.disabled = false;
        }else{
            update.style.display = "none";
            entry_title.disabled = true;
            entry_body.disabled = true;
        }
        current_id = entry.id;
        scope['title'] = entry.title;
        scope['data'] = entry.content;
    })
}

const fetchList = ()=>{
    fetch(baseURL+"/entries")
    .then(data=>data.json())
    .then(data=>{
        scope['entry_list'] = data;
        let container = document.getElementById('entries');

        if (scope['entry_list'].length <= 0){
            li = document.createElement('li');
            li.textContent = "No entries yet!!!";
            container.appendChild(li);
            return;
        }

        for(let page of scope['entry_list']){
            console.log(page)
            //Creating an li
            li = document.createElement('li');
            AddButtonHandler(li,page);
            li.addEventListener('mouseleave',removeButton);
            p = document.createElement("p");
            p.textContent = page.title + " " + page.date;
            // p.addEventListener('click',loadPrevious(page));
            LoadPreviousHandler(p,page);
            // p.onclick = loadPrevious(page)
            li.appendChild(p);
            container.appendChild(li);

            if (page.date === today){
                scope['title'] = page.title;
                scope['data'] = page.content;
            }
        }

        let last_entry = scope['entry_list'][scope['entry_list'].length - 1];
        let entry_date = new Date(last_entry.date);
        entry_date = entry_date.getDate()+"/"+entry_date.getMonth()+"/"+entry_date.getFullYear();
        if (entry_date === today){
            current_id = last_entry.id;
            scope['title'] = last_entry.title;
            scope['data'] = last_entry.content;
        }else{
            current_id = 0;
            scope['title'] = "";
            scope['data'] = "";
        }
    });
}
const setup = ()=>{
    update.style.display = "none";
    back.style.display = "none";
    submit.style.display = "block"; 

    fetchList();
}

// TODO: verify correctness
const updateEntry = () =>{
    console.log("update clicked");
    // sending the update
    fetch(baseURL+"/diaryentry/"+current_id,{
        method:"PUT",
        body:{
            entry:{
                title: scope['title'],
                content: scope['data'],
            }
        }
    }).then((response) =>{
        console.log(response);
        if (response.status == 200){
            alert("Entry updated successfully");
            fetchList();
        }else{
            alert("There was some issue on the server end!!");
        }
    })
}

// TODO: verify the correctness
const submitEntry = () =>{
    console.log("Submit clicked");
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
    //             id: scope['entry_list'][scope['entry_list'].length - 1].id + 1,
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

const loadCurrent = () =>{
    back.style.display = "none";
    submit.style.display = "block"; 
    entry_title.disabled = false;
    entry_body.disabled = false;

    let last_entry = scope['entry_list'][scope['entry_list'].length - 1];
    if (last_entry.date === today){
        current_id = last_entry.id;
        scope['title'] = last_entry.title;
        scope['data'] = last_entry.content;
    }else{
        current_id = 0;
        scope['title'] = "";
        scope['data'] = "";
    }
    

}

const unhighlight = () =>{
    document.getElementById("entry-title").style = "font-family: 'Space Mono', monospace;font-size: 1rem; color: black;border: none;"
}

const highlight = () =>{
    document.getElementById("entry-title").style = "font-family: 'Space Mono', monospace;font-size: 1rem; color: lightcoral;border: 0.15vw solid lightcoral;";
}

const removeButton = (e) =>{
    const del = document.getElementById("delete");
    e.target.removeChild(del);
}