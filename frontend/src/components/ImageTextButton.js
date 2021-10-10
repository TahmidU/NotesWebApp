import React from 'react';
import '../styles/button.css';
import '../styles/image_text_button.css';
import { Link } from 'react-router-dom';

const SIZES = ['btn-small', 'btn-medium'];
const IMG_TEXT_STYLES = ['', 'img-left-txt-right', 'img-right-txt-left', 'img-top-txt-btm', 'img-btm-txt-top'];
const STYLES = ['', 'btn-clear-blue', 'btn-round-blue', 'btn-round-red'];

export const ImageTextButton = (props) => {

    const checkBtnSize = SIZES.includes(props.btnSize) ? props.btnSize : SIZES[0];
    const checkBtnStyle = STYLES.includes(props.btnStyle) ? props.btnStyle : STYLES[0];
    const checkImgTextPos = IMG_TEXT_STYLES.includes(props.imgTextPos) ? props.imgTextPos : IMG_TEXT_STYLES[0];
    const checkTo = props.to == null ? '/' : props.to;
    const checkClassName = props.className == null ? '' : props.className;

    if(props.isLink){

        return(
            <Link className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkImgTextPos} ${checkClassName}`} to={checkTo}>
                <div>{props.image}</div>
                <p>{props.children}</p>
            </Link>
        );

    }

    return(
        <button className={`btn ${checkBtnSize} ${checkBtnStyle} ${checkImgTextPos} ${checkClassName}`} onClick={props.onClick}>
            <div>{props.image}</div>
            <p>{props.children}</p>
        </button>
    );

}