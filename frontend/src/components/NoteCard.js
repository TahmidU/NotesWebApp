import React, {useRef} from 'react';
import '../styles/note_card.css';
import EditImg from '../img/edit.svg';
import DeleteImg from '../img/trash.svg';
import { Button } from '../components/Button';
import { ImageButton } from './ImageButton';

export const NoteCard = ({note}) => {

    const contentEltRef = useRef();

    function toggleContentExpand(){

        if(contentEltRef.current.style.display === 'none'){
            contentEltRef.current.style.display = 'block';
        }else{
            contentEltRef.current.style.display = 'none';
        }
    }

    return(
        <>
        <li key={note.id} className='card'>
            <div className='note-title-section'>
                <div>
                    <p>{note.title}</p>
                </div>
                <ImageButton image={EditImg} alt='edit' isLink={true} to={`noteboard/edit/${note.id}`}/>
                <ImageButton image={DeleteImg} alt='delete'/>
            </div>
            <Button className='expand-btn' isLink={false} btnSize='btn-small' btnStyle='btn-text-blue' onClick={toggleContentExpand}>{[['+ Expand'], ['- Collapse']]}</Button>
            <div ref={contentEltRef} className='note-desc' style={{display:'none'}}>
                <p>{note.content}</p>
            </div>
        </li>
        </>
    );

}