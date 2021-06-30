
    //Variabeln Definieren für die Tabelle die ausgegeben wird hier ganz einfach gehalten mit Table/TH/TR/TD/OLDData
    let table;
    let th;
    let tr;
    let td;
    let oldFiles;

    //Macht eine Anfrage um ein Json Object zu bekommen
    let getJSON = function (url, callback) {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = 'json';
        xhr.onLoad = function () {
            let status = xhr.status;
            if (status === 200) {
                callback(null, xhr.response);
            } else {
                callback(status, xhr.response);
            }
        };
        xhr.send();
    };
    $(document).ready(function(){
    repeatStart();
    $("#hasten").click(function(){
    hasten();
});
    $("#break").click(function(){
    breaking();
});
    $("#stop").click(function(){
    stop();
});
});

    function repeatStart()
    {
        table = document.createElement("TABLE");
        document.getElementById("table").appendChild(table);

        //Tabelle wird aktualisiert
        setInterval(function(){createTable()}, 1000);
    }

    function createTable()
    {
        getJSON('/getPlatoon', function(err, files){
            if (err !== null) {
                console.log('FEHLER! ' + err);
            } else {
                //Ändert nur die Daten wenn sie Neu sind.
                if (files !== oldF)
                {
                    removeElements();
                    initializeTable();
                    for (i=0; i< files.length; i++)
                    {
                        appendTableInformation(files[i]);
                    }
                    oldF = files;
                }
            }
        });
    }

    function breaking()
    {
        let vehicleSpeed = document.getElementById("textBreak").value;
        getJSON('/break/' + vehicleSpeed, function(err, files){
        if (err !== null) {
        console.log('FEHLER!' + err);
    }
    });
    }

    function hasten()
    {
        let vehilceSpeed = document.getElementById("textHasten").value;
        getJSON('/hasten/' + vehilceSpeed, function(err, files){
        if (err !== null) {
        console.log('FEHLER!' + err);
    }
    });
    }

    function stop()
    {
        getJSON('/stop', function(err, files){
            if (err !== null) {
                console.log('FEHLER! ' + err);
            }
        });
    }

    //Erstellt den Header für die Daten
    function initializeTable()
    {
        tr = document.createElement("TR");
        table.appendChild(tr);
        th = document.createElement("TH");
        th.innerHTML = "position";
        tr.appendChild(th);
        th = document.createElement("TH");
        th.innerHTML = "id";
        tr.appendChild(th);
        th = document.createElement("TH");
        th.innerHTML = "frontID";
        tr.appendChild(th);
        th = document.createElement("TH");
        th.innerHTML = "backID";
        tr.appendChild(th);
        th = document.createElement("TH");
        th.innerHTML = "isMaster";
        tr.appendChild(th);
        th = document.createElement("TH");
        th.innerHTML = "vehicleSpeed";
        tr.appendChild(th);
    }

    //Füllt die Tabelle mit Daten
    function appendTableInformation(files)
    {
        tr = document.createElement("TR");
        table.appendChild(tr);
        td = document.createElement("TD");
        td.innerHTML = files.positionId;
        tr.appendChild(td);
        td = document.createElement("TD");
        td.innerHTML = files.id;
        tr.appendChild(td);
        td = document.createElement("TD");
        td.innerHTML = files.frontID;
        tr.appendChild(td);
        td = document.createElement("TD");
        td.innerHTML = files.backID;
        tr.appendChild(td);
        td = document.createElement("TD");
        td.innerHTML = files.isMaster;
        tr.appendChild(td);
        td = document.createElement("TD");
        td.innerHTML = files.vehicleSpeed;
        tr.appendChild(td);
    }
