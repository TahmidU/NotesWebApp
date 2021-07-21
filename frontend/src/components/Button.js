import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/button.css';

const SIZES = ['btn-medium', 'btn-large'];
const STYLES = ['btn-round-blue'];


export const Button = ({children, btnSize, btnStyle, isNavBtn, to}) => {


    const checkBtnSize = SIZES.includes(btnSize) ? btnSize : SIZES[0];
    const checkBtnStyle = STYLES.includes(btnStyle) ? btnStyle : STYLES[0];

    if(isNavBtn){
        return(
            <Link className={`btn nav-btn ${checkBtnSize} ${checkBtnStyle}`} to={to}>{children}</Link>
        );
    }

    return(
        <Link className={`btn ${checkBtnSize} ${checkBtnStyle}`} to={to}>{children}</Link>
    );
}