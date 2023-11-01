import React from 'react'
import { Button, Form, Grid, Image, Input, Table } from 'semantic-ui-react'
import LinkForm from './LinkForm'

function LinkTable({ links, linkUrl, linkDescription, linkTextSearch, handleInputChange, handleAddLink, handleDeleteLink, handleSearchLink }) {
  let linkList
  if (links.length === 0) {
    linkList = (
      <Table.Row key='no-link'>
        <Table.Cell collapsing textAlign='center' colSpan='4'>No link</Table.Cell>
      </Table.Row>
    )
  } else {
    linkList = links.map(link => {
      return (
        <Table.Row key={link.id}>
          <Table.Cell collapsing>
            <Button
              circular
              color='red'
              size='small'
              icon='trash'
              onClick={() => handleDeleteLink(link.id)}
            />
          </Table.Cell>
          <Table.Cell>
            <Image src={`http://covers.openlibrary.org/b/isbn/${link.id}-M.jpg`} size='tiny' bordered rounded />
          </Table.Cell>
          <Table.Cell>{link.url}</Table.Cell>
          <Table.Cell>{link.description}</Table.Cell>
        </Table.Row>
      )
    })
  }

  return (
    <>
      <Grid stackable divided>
        <Grid.Row columns='2'>
          <Grid.Column width='5'>
            <Form onSubmit={handleSearchLink}>
              <Input
                action={{ icon: 'search' }}
                name='linkTextSearch'
                placeholder='Search by URL or Description'
                value={linkTextSearch}
                onChange={handleInputChange}
              />
            </Form>
          </Grid.Column>
          <Grid.Column>
            <LinkForm
              linkUrl={linkUrl}
              linkDescription={linkDescription}
              handleInputChange={handleInputChange}
              handleAddLink={handleAddLink}
            />
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <Table compact striped selectable>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell width={1}/>
            <Table.HeaderCell width={3}>Cover</Table.HeaderCell>
            <Table.HeaderCell width={4}>URL</Table.HeaderCell>
            <Table.HeaderCell width={8}>Description</Table.HeaderCell>
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {linkList}
        </Table.Body>
      </Table>
    </>
  )
}

export default LinkTable