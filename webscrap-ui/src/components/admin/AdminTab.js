import React from 'react'
import { Tab } from 'semantic-ui-react'
import UserTable from './UserTable'
import LinkTable from './LinkTable'

function AdminTab(props) {
  const { handleInputChange } = props
  const { isUsersLoading, users, userUsernameSearch, handleDeleteUser, handleSearchUser } = props
  const { isLinksLoading, links, linkUrl, linkDescription, linkTextSearch, handleAddLink, handleDeleteLink, handleSearchLink } = props

  const panes = [
    {
      menuItem: { key: 'users', icon: 'users', content: 'Users' },
      render: () => (
        <Tab.Pane loading={isUsersLoading}>
          <UserTable
            users={users}
            userUsernameSearch={userUsernameSearch}
            handleInputChange={handleInputChange}
            handleDeleteUser={handleDeleteUser}
            handleSearchUser={handleSearchUser}
          />
        </Tab.Pane>
      )
    },
    {
      menuItem: { key: 'links', icon: 'linkify', content: 'Links' },
      render: () => (
        <Tab.Pane loading={isLinksLoading}>
          <LinkTable
            links={links}
            linkUrl={linkUrl}
            linkDescription={linkDescription}
            linkTextSearch={linkTextSearch}
            handleInputChange={handleInputChange}
            handleAddLink={handleAddLink}
            handleDeleteLink={handleDeleteLink}
            handleSearchLink={handleSearchLink}
          />
        </Tab.Pane>
      )
    }
  ]

  return (
    <Tab menu={{ attached: 'top' }} panes={panes} />
  )
}

export default AdminTab