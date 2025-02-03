(function header() {
    document.getElementById('header').innerHTML = `
    <div id="header-frame" class="header-frame">
        <div class="leftBox">
            <!--<img class="responsive-image" src="images/icon.png" alt="Icon">-->
        </div>
        <div class="rightBox">
            <div class="menuButton" onclick="window.location.href='/';">
                <div class="menuText">Home</div>
            </div>
        </div>
    </div>
    `;
})();

(function footer() {
    document.getElementById('footer').innerHTML = `
    <div class="footer-frame">
        <div class="box1-3">
                <div class="footerText">+48 222 111 555</div>
                <div class="footerText">The helpline is available from Monday to Friday, from 9:00 AM to 9:00 PM.</div>
            </div>
            <div class="box1-3">
                <div class="footer-mainText">Customer Service Office</div>
                <!--<img class="responsive-image" src="images/icon.png" alt="Icon">-->
            </div>
            <div class="box1-3">
                <div class="footerText">StockApp@StockApp.com</div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="footerCopyright">© 2024 Stock App. All rights reserved</div>
        </div>
    </div>`;
})();

window.addEventListener('scroll', function () {
    const header = document.getElementById('header-frame');
    if (window.scrollY > 0) {
        header.classList.add('scrolled');
    } else {
        header.classList.remove('scrolled');
    }
});