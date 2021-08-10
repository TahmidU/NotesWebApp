import React, {useRef} from 'react';
import '../styles/note_card.css';
import EditImg from '../img/edit.svg';
import DeleteImg from '../img/trash.svg';
import UnexpandedImg from '../img/unexpanded.svg';
import ExpandedImg from '../img/expanded.svg';
import { Button } from '../components/Button';
import { ImageButton } from './ImageButton';

export const NoteCard = ({note}) => {

    const contentEltRef = useRef();
    const expandToggleEltRef = useRef();

    function toggleContentExpand(){

        if(contentEltRef.current.style.display == 'none'){
            contentEltRef.current.style.display = 'block';
        }else{
            contentEltRef.current.style.display = 'none';
        }
    }

    return(
        <>
        <li key={note.id}>
            <div className='note-title-section'>
                <div>
                    <p>{note.title}</p>
                </div>
                <button id='edit-btn' type='button'>
                    <img src={EditImg}/>
                </button>
                <button id='delete-btn' type='button'>
                    <img src={DeleteImg}/>
                </button>
            </div>
            <Button className='expand-btn' isLink={false} btnSize='btn-small' btnStyle='btn-text-blue' onClick={toggleContentExpand}>{[['+ Expand'], ['- Collapse']]}</Button>
            <div ref={contentEltRef} className='note-desc' style={{display:'none'}}>
                <p>{note.content}</p>
            </div>
        </li>
        </>
    );

}