import React from 'react';

function Header() {
    return (
        <header>
            <div id="header-frame" className="header-frame">
                <div className="leftBox">
                    {/*<img className="responsive-image" src="images/icon.png" alt="Icon" />*/}
                </div>
                <div className="rightBox">
                    <div className="menuButton" onClick={() => window.location.href = '/'}>
                        <div className="menuText">Home</div>
                    </div>
                </div>
            </div>
        </header>
    );
}

window.addEventListener('scroll', function () {
    const header = document.getElementById('header-frame');
    if (window.scrollY > 0) {
        header.classList.add('scrolled');
    } else {
        header.classList.remove('scrolled');
    }
});

export default Header;