import React, { useEffect, useState } from 'react';
import '../styles/noteboard.css';
import { Layout } from '../components/Layout';
import { NoteCard } from '../components/NoteCard';
import axios from 'axios';
import { Button } from '../components/Button';

export const Noteboard = () => {

    const [contents, setContents] = useState({
        notes: [],
        page: 0,
        totalPages: 0
    });

    useEffect(getNotes, [contents.page]);

    function getNotes(){

        axios.get(`http://192.168.0.19:8080/api/note?page=${contents.page}`)
        .then(response => {
            setContents((prevState) => {
                return{
                    notes: response.data.content,
                    page: response.data.number,
                    totalPages: response.data.totalPages
                }
            });

        }).catch(error => console.log(error));
    }

    function refreshNotes(){

        if(contents.page !== 0){
            setContents((prevState) => {
                return{
                    notes: prevState.notes,
                    page: 0,
                    totalPages: prevState.totalPages
                }
            });
        }else{
            getNotes();
        }
    }

    return(
        <Layout>
            <div className='noteboard-container'>
                <div className='search-container'>
                </div>
                <div className='notes-list-container'>
                    <ul>
                        {contents.notes.map(note => {
                            return(
                                <NoteCard key={note.noteId} note={note} onDelete={refreshNotes}/>
                            );
                        })}
                    </ul>
                    
                </div>
                <div className='page-num-container'>
                    
                    {Array.from(Array(contents.totalPages), (e, i) => {

                        if(i === contents.page)
                            return <Button key={i} btnStyle='btn-text-selected' btnSize='btn-medium'>{i+1}</Button>
                        
                        return <Button key={i} btnStyle='btn-text-blue' btnSize='btn-medium' onClick={() => setContents({...contents, page:i})}>{i+1}</Button>
                    })}
                </div>
            </div>
        </Layout>
    );

}