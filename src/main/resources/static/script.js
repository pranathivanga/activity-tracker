fetch("http://localhost:8080/api/heatmap?year=2026&month=1")
    .then(res => res.json())
    .then(data => {
        const grid = document.getElementById("grid");

        Object.values(data).forEach(level => {
            const div = document.createElement("div");
            div.classList.add("box");
            if (level > 0) div.classList.add("l" + level);
            grid.appendChild(div);
        });
    });
