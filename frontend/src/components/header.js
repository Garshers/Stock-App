import React from 'react';

function Header() {
    return (
        <header>
            <div id="header-frame" className="header-frame">
                <div className="leftBox">
                    {/*<img className="responsive-image" src="images/icon.png" alt="Icon" />*/}
                </div>
                <div className="rightBox">
                    <div className="menuButton" onClick={() => window.location.href = '/'}> {/* Poprawione onClick */}
                        <div className="menuText">Home</div>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;