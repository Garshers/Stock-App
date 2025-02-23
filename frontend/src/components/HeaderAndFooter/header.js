import React, { useState, useEffect } from 'react';

export function Header() {
    const [isScrolled, setIsScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            setIsScrolled(window.scrollY > 0);
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    return (
        <header>
            <div className='header-space'></div>
            <div id="header-frame" className={`header-frame ${isScrolled ? 'scrolled' : ''}`}>
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