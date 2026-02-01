// ------------------------------
// DOMContentLoaded: only event binding
// ------------------------------
document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll('.toggle-properties').forEach(btn => {
        btn.addEventListener('click', function () {

            const row = this.closest('.line-item');
            const id = row.dataset.id;
            const type = row.dataset.type;
            const panel = document.getElementById('props-' + id);

            if (panel.style.display === 'none') {
                panel.style.display = '';
                loadPropertiesPanel(panel, type, id);
            } else {
                panel.style.display = 'none';
            }
        });
    });

});


// ------------------------------
// Global functions (visible to HTML onclick)
// ------------------------------

function expandAllDetails() {
    document.querySelectorAll('.line-item').forEach(row => {
        const id = row.dataset.id;
        const panel = document.querySelector(`#props-${id}`);
        if (panel) {
            loadPropertiesPanel(panel, row.dataset.type, id);
            panel.style.display = 'table-row';
        }
    });
}

function collapseAllDetails() {
    document.querySelectorAll('.properties-panel').forEach(panel => {
        panel.style.display = 'none';
    });

    document.querySelectorAll('.line-item, .properties-panel')
        .forEach(row => row.classList.remove('highlight-row', 'highlight-border'));
}

function loadPropertiesPanel(panel, type, id) {

    document.querySelectorAll('.line-item, .properties-panel')
        .forEach(row => row.classList.remove('highlight-row', 'highlight-border'));

    const mainRow = document.querySelector(`.line-item[data-id="${id}"]`);
    if (mainRow) {
        mainRow.classList.add('highlight-row', 'highlight-border');
    }

    const propsRow = document.getElementById(`props-${id}`);
    if (propsRow) {
        propsRow.classList.add('highlight-row');
    }

    const propsJson = mainRow.dataset.props || '{}';
    const props = JSON.parse(propsJson);

    const html = renderPropertiesFragment(props);

    panel.querySelector('td').innerHTML = html;
}

function renderPropertiesFragment(props) {
    let html = `
        <div class="spec-panel">
            <div class="spec-header">Specifications</div>
            <div class="spec-grid">
    `;

    for (const [key, value] of Object.entries(props)) {
        html += `
            <div class="spec-item">
                <span class="spec-bullet">•</span>
                <span class="spec-key">${formatKey(key)}:</span>
                <span class="spec-value">${value}</span>
            </div>
        `;
    }

    html += `
            </div>
        </div>
    `;

    return html;
}

function formatKey(key) {
    return key
        .replace(/([a-z])([A-Z])/g, '$1 $2')
        .replace(/^./, c => c.toUpperCase());
}


function loadAddEquipmentSpecPanel(properties) {
    const panel = $("#addEquipmentSpecPanel");
    const grid = $("#addEquipmentSpecGrid");

    grid.empty();

    for (const [key, value] of Object.entries(properties)) {
        grid.append(`
            <div class="spec-item">
                <span class="spec-bullet">•</span>
                <span class="spec-key">${formatKey(key)}:</span>
                <span class="spec-value">${value}</span>
            </div>
        `);
    }

    panel.show();
}