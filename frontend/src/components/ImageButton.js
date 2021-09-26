import React from 'react';
import '../styles/image_button.css';
import { Link } from 'react-router-dom';

const SIZES = ['img-btn-small', 'img-btn-logo'];
const IMG_TEXT_STYLES = ['', 'img-left-txt-right', 'img-right-txt-left', 'img-top-txt-btm', 'img-btm-txt-top']

export const ImageButton = (props) => {

    const checkBtnSize = SIZES.includes(props.btnSize) ? props.btnSize : SIZES[0];
    const checkImgTxtStyles = IMG_TEXT_STYLES.includes(props.imgTextStyle) ? props.imgTextStyle : IMG_TEXT_STYLES[0];
    const checkTo = props.to == null ? '/' : props.to;
    const checkClassName = props.className == null ? '' : props.className;

    if(props.isLink){

        if(props.image != null && typeof props.children === 'string'){
            return(
                <Link>
                    <div>{props.image}</div>
                    <p>{props.children}</p>
                </Link>
            );
        }

        return(
            <Link className={`img-btn ${checkBtnSize} ${checkClassName}`} to={checkTo}>
                <img src={props.image} alt={props.alt}/>
            </Link>
        );

    }

    if(props.image != null && typeof props.children === 'string'){

        console.log(props.image);

        return(
            <button className={`img-btn ${checkBtnSize} ${checkImgTxtStyles} ${checkClassName}`} onClick={props.onClick}>
                <div>{props.image}</div>
                <p>{props.children}</p>
            </button>
        );
    }

    return(
        <button className={`img-btn ${checkBtnSize} ${checkClassName}`} onClick={props.onClick}>
            <img src={props.image} alt={props.alt}/>
        </button>
    )

}