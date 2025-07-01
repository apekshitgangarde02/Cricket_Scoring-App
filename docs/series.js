const apiBaseUrl = "http://localhost:8080/api";
document.getElementById('series-form').addEventListener('submit', async function (event) {
    event.preventDefault();

    const seriesName = document.getElementById('seriesName').value;
    const createdBy = document.getElementById('createdBy').value;
    const team1Name = document.getElementById('team1Name').value;
    const team2Name = document.getElementById('team2Name').value;
    const playersPerTeam = document.getElementById('playersPerTeam').value;
    const overs = document.getElementById('overs').value;
    const team1Players = document.getElementById('team1Players').value.split(',');
    const team2Players = document.getElementById('team2Players').value.split(',');

    const seriesData = {
        seriesName,
        createdBy,
        team1Name,
        team2Name,
        playersPerTeam,
        overs,
        team1Players,
        team2Players
    };

    try {
        const response = await fetch(`${apiBaseUrl}/series`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(seriesData),
        });

        if (response.ok) {
            const message = await response.text();
            document.getElementById('message').innerText = message;
            document.getElementById('message').style.color = 'green';
            window.location.href = 'match.html'; 
        } else {
            const errorMessage = await response.text();
            document.getElementById('message').innerText = errorMessage;
            document.getElementById('message').style.color = 'red';
        }
    } catch (error) {
        document.getElementById('message').innerText = "Error: " + error.message;
        document.getElementById('message').style.color = 'red';
    }
});
