import React from 'react';

function Footer() {
    return (
        <footer>
            <div className="footer-frame">
                <div className="box1-3">
                    <div className="footerText">+48 222 111 555</div>
                    <div className="footerText">
                        The helpline is available from Monday to Friday, from 9:00 AM to 9:00 PM.
                    </div>
                </div>
                <div className="box1-3">
                    <div className="footer-mainText">Customer Service Office</div>
                    {/*<img className="responsive-image" src="images/icon.png" alt="Icon" />*/}
                </div>
                <div className="box1-3">
                    <div className="footerText">StockApp@StockApp.com</div>
                </div>
            </div>
            <div className="footer-copyright">
                <div className="footerCopyright">© 2024 Stock App. All rights reserved</div>
            </div>
        </footer>
    );
}

export default Footer;