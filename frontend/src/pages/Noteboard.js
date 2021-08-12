import React, { useEffect, useState } from 'react';
import '../styles/noteboard.css';
import { Layout } from '../components/Layout';
import { NoteCard } from '../components/NoteCard';
import axios from 'axios';

export const Noteboard = () => {

    const [contents, setContents] = useState({
        notes: [],
        page: 0
    });

    useEffect(refreshNotes, [contents.page]);

    function refreshNotes(){
        axios.get(`http://localhost:8080/api/note?page=${contents.page}`)
        .then(response => {
            console.log(response.data);
            setContents({
                notes: response.data.content,
                page: response.data.number
            });

        }).catch(error => console.log(error));
    }

    return(
        <Layout>
            <div style={{height:'100vh'}}>
                <div className='search-container'>
                </div>
                <div className='notes-list-container' style={{display:'flex', justifyContent:'center'}}>
                    <ul style={{width: '80%', listStyleType:'none'}}>
                        {contents.notes.map(note => {
                            return(
                                <NoteCard key={note.noteId} note={note} onDelete={refreshNotes}/>
                            );
                        })}
                    </ul>
                    
                </div>
            </div>
        </Layout>
    );

}