import React, {useRef} from 'react';
import '../styles/note_card.css';
import EditImg from '../img/edit.svg';
import DeleteImg from '../img/trash.svg';
import { ImageButton } from './ImageButton';
import axios from 'axios';
import { ToggleButton } from './ToggleButton';

export const NoteCard = ({note, onDelete}) => {

    const contentEltRef = useRef();

    function toggleContentExpand(){

        if(contentEltRef.current.style.display === 'none'){
            contentEltRef.current.style.display = 'block';
        }else{
            contentEltRef.current.style.display = 'none';
        }
    }

    function deleteNote(id){
        axios.delete(`http://localhost:8080/api/note/${id}`).then(onDelete);
    }

    return(
        <li className='card'>
            <div className='note-title-section'>
                <div>
                    <p>{note.title}</p>
                </div>
                <ImageButton image={EditImg} alt='edit' isLink={true} to={`/edit-note/${note.noteId}`}/>
                <ImageButton image={DeleteImg} alt='delete' onClick={() => deleteNote(note.noteId)}/>
            </div>
            <ToggleButton className='expand-btn' btnSize='btn-small' btnStyle='btn-text-blue' onClick={toggleContentExpand}>{[['+ Expand'], ['- Collapse']]}</ToggleButton>
            <div ref={contentEltRef} className='note-desc' style={{display:'none'}}>
                <p>{note.content}</p>
            </div>
        </li>
    );

}